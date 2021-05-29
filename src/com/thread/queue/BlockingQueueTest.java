package com.thread.queue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockingQueueTest {
    //指定阻塞队列的大小(容量)
    private static final int FILE_QUEUE_SIZE = 10;
    //查找目录的线程数
    private static final int SEARCH_THREADS = 100;
    //定义一个Path实例用于标记队尾
    private static final Path DUMMY = Path.of("");
    //构造一个有指定初始容量大小的阻塞队列
    private static BlockingQueue<Path> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try (var in = new Scanner(System.in)){
            //输入要查找的目录和相匹配的关键字
            System.out.print("Enter base directory (e.g. /opt.jdk-11-src): ");
            String directory = in.nextLine();
            System.out.println("Enter keyword (e.g. volatile): ");
            String keyword = in.nextLine();
            //在创建的第一个线程中添加文件到阻塞队列中, 之后创建100个线程用于查找文件
            Runnable enumerator = ()->{
                try {
                    //枚举该目录的子目录, 并将该目录下的子目录添加进入阻塞队列中
                    enumerate(Path.of(directory));
                    //向阻塞队列中添加结尾元素, 必要时
                    queue.put(DUMMY);
                }catch (IOException e){
                    e.printStackTrace();
                }catch (InterruptedException e){}
            };
            //开启线程, 不断将子目录添加进入阻塞队列中
            new Thread(enumerator).start();
            //开启100个查找目录的线程
            for (int i = 0; i < SEARCH_THREADS; i++) {
                Runnable searcher = ()->{
                    try {
                        //标记done表示是否完成查找
                        var done = false;
                        //若未完成查找则继续查找
                        while(!done){

                            Path file = queue.take();
                            //若查找到了队尾
                            if (file == DUMMY){
                                //将移出的队尾元素重新移入阻塞队列
                                queue.put(file);
                                //标记查找结束
                                done = true;
                            }else {
                                //若不是队尾文件, 则查找该文件是否含有关键字
                                search(file, keyword);
                            }
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    }catch (InterruptedException e){ }
                };
                new Thread(searcher);
            }
        }
    }
    //用于枚举目录下的子目录并将目录添加进入阻塞队列中
    public static void enumerate(Path directory) throws IOException, InterruptedException {
        //初始化时将目录的下的子项以流的形式返回
        try (Stream<Path> children = Files.list(directory)) {
            //遍历子文件
            for (Path child :
                    children.collect(Collectors.toList())) {
                //如果子项还是目录
                if (Files.isDirectory(child)) {
                    //继续遍历子项
                    enumerate(child);
                } else {
                    //如果子项不是目录则将文件添加到阻塞队列中, 如果队列满则阻塞
                    queue.put(child);
                }
            }
        }
    }
    //文件查找函数
    public static void search(Path file, String keyword) throws IOException{
        try (var in = new Scanner(file, StandardCharsets.UTF_8)){
            //标记查找行数
            int lineNumber = 0;
            ////如果存在数据则按行单位查找
            while (in.hasNextLine()){
                lineNumber++;
                String line = in.nextLine();
                if (line.contains(keyword))
                    //输出关键字所在文件, 行数, 行内信息
                    System.out.printf("%s:%d:%n", file, lineNumber, line);
            }
        }
        byte a = -128;
        
    }
}
