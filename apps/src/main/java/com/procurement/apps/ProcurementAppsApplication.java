package com.procurement.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class ProcurementAppsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProcurementAppsApplication.class, args);
		int arr[] = {3,6,2,5,1};
		int a[] = {1,4,5};
		int b[] = {3,4,9};
		var result = FindMinimumAmount(arr);
		System.out.println(result);
		var result2 = FindMaximumHeight(a,b);
		System.out.println(result2);
	}

	public static int FindMinimumAmount(int arr[]) {
		int[] newArr = Arrays.copyOfRange(arr, 0,3);
		Arrays.sort(newArr);
		var freeValue = Arrays.stream(newArr).min().getAsInt();
		var result = new ArrayList<Integer>();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != freeValue && result.size() < 4) {
				result.add(arr[i]);
			}
		}
		return result.stream().mapToInt(i -> i).sum();
	}

	public static int FindMaximumHeight(int a[], int b[]){
		int[] tempA = new int[a.length];
		int[] tempB = new int[b.length];

		for (int i = 0; i < a.length; i++) {
			if (i == 0){
				tempA[i] = a[i];
			} else if (i % 2 != 0){
				tempA[i] = b[i];
			} else if (i % 2 == 0){
				tempA[i] = a[i];
			}
		}

		for (int i = 0; i < b.length; i++) {
			if (i == 0){
				tempB[i] = b[i];
			} else if (i % 2 != 0){
				tempB[i] = a[i];
			} else if (i % 2 == 0){
				tempB[i] = b[i];
			}
		}
		var sumA = Arrays.stream(tempA).sum();
		var sumB = Arrays.stream(tempB).sum();
		if (sumA > sumB) return sumA;
		return sumB;
	}
}
