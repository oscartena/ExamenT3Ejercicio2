package com.example.exament3ejercicio2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ObservableList<UrlInfo> urlList = FXCollections.observableArrayList();
        DownloaderAndZipper downloaderAndZipper = new DownloaderAndZipper();
        urlList.addListener(downloaderAndZipper);

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.print("Introduce una URL (o 'exit' para salir): ");
            String url = sc.nextLine();

            if ("exit".equalsIgnoreCase(url)) {
                break;
            }

            urlList.add(new UrlInfo(url, generateUniqueString()));
        }
        sc.close();
    }

    private static String generateUniqueString() {
        Random random = new Random();
        StringBuilder uniqueString = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            uniqueString.append(randomChar);
        }
        return uniqueString.toString();
    }
}

class UrlInfo {
    private String url;
    private String uniqueString;

    public UrlInfo(String url, String uniqueString) {
        this.url = url;
        this.uniqueString = uniqueString;
    }

    public String getUrl() {
        return url;
    }

    public String getUniqueString() {
        return uniqueString;
    }
}