package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException{
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = bufferedReader.readLine();
            while (!"exit".equals(fileName)) {
                new ReadThread(fileName).start();
                fileName = bufferedReader.readLine();
        }
            bufferedReader.close();
//        System.out.println(resultMap.values());
    }

        public static class ReadThread extends Thread {

            private String fileName;
            ArrayList<Integer> list = new ArrayList<>();

            public ReadThread(String fileName){
                this.fileName = fileName;
            }

            @Override
            public void run() {

                try {
                    FileInputStream fileInputStream = new FileInputStream(fileName);
                    while (fileInputStream.available() > 0) list.add(fileInputStream.read());
                    fileInputStream.close();
                } catch (IOException ioException ) {
                    ioException.printStackTrace();
                }

                int element = list.get(0);
                for (Integer x : list) if (Collections.frequency(list, x) > Collections.frequency(list, element)) element = x;
                resultMap.put(fileName, element);

            }
        }
    }

