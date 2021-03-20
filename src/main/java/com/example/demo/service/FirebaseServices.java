package com.example.demo.service;

import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.example.demo.object.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

@Service
public class FirebaseServices {
	
	public String saveUser(User data) throws InterruptedException, ExecutionException {
        Firestore db = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> future =
				db.collection("users").document(data.getId()).set(data);
        return future.get().getUpdateTime().toString();
    }

	public User getUser(String id) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		DocumentReference docRef = db.collection("users").document(id);
		// asynchronously retrieve the document
		ApiFuture<DocumentSnapshot> future = docRef.get();
		// block on response
		DocumentSnapshot document = future.get();
		User user = null;
		if (document.exists()) {
		  // convert document to POJO
		  user = document.toObject(User.class);
		  System.out.println(user);
		  return user;
		} else {
		  System.out.println("No such document!");
		  return null;
		}
	}
	
	public String deleteUser(String id) throws InterruptedException, ExecutionException {
		Firestore db = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> writeResult = db.collection("users").document(id).delete();
		return writeResult.get().getUpdateTime().toString();
	}
}
