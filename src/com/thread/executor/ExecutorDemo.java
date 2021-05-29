package com.thread.executor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 本程序:1. 统计文件夹下所有文件中指定单词出现的次数, 2. 查找指定的单词
 * */
public class ExecutorDemo {
    public static long occurrences(String word, Path path){
        try (var in = new Scanner(path)){
            int count = 0;
            while (in.hasNext())
                if (in.next().equals(word)) count++;//返回在
                return count;
        }catch (IOException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException{
        try (Stream<Path> entries = Files.walk(rootDir)){//遍历以rootDir为根目录的文件夹
            return entries.filter(Files::isRegularFile).collect(Collectors.toSet());//返回常规文件(不是目录)
        }
    }
    //在指定文件中查找关键字
    public static Callable<Path> searchForTask(String word, Path path){
        return () -> {
            try (var in = new Scanner(path)){
                while (in.hasNext()){
                    if (in.next().equals(word)) return path;
                    if (Thread.currentThread().isInterrupted()){
                        System.out.println("Search in" + path + "canceled");
                        return null;
                    }
                }
            }
            throw new NoSuchElementException();
        };
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException, IOException {
        try (var in = new Scanner(System.in)) {
            System.out.print("Enter base directory: ");
            String start = in.nextLine();
            System.out.print("Enter keyword:");
            String keyword = in.nextLine();

            Set<Path> files = descendants(Path.of(start));
            var tasks = new ArrayList<Callable<Long>>();
            for (Path file : files) {
                Callable<Long> task = () -> occurrences(keyword, file);
                tasks.add(task);
            }
            //创建构造器(线程池)
            ExecutorService executorService = Executors.newCachedThreadPool();
            //记录开始时间
            Instant startTime = Instant.now();
            List<Future<Long>> result = executorService.invokeAll(tasks);
            long total = 0;
            for (Future<Long> future: result)
                total += future.get();
            Instant endTime = Instant.now();
            System.out.println("Occurrences of " + keyword + "" + total);
            System.out.println("Time elapsed: " + Duration.between(startTime, endTime).toMillis() + "ms");
            var searchTasks = new ArrayList<Callable<Path>>();
            for (Path file: files){
                searchTasks.add(searchForTask(keyword, file));
            }
            Path found = executorService.invokeAny(searchTasks);
            System.out.println(keyword + "occurs in: " + found);
            if (executorService instanceof ThreadPoolExecutor)
                System.out.println("Largest pool size: " + ((ThreadPoolExecutor)executorService).getLargestPoolSize());
            executorService.shutdown();
        }
    }


}
