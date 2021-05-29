package test;

//定义回调函数接口
interface CallBack{
    public void execute();
}

public class Main{
    public static void main(String[] args) {
        //处理业务
        var testBookCallBack = new TestBookCallBack();
        testBookCallBack.test(new CallBack() {//定义匿名内部类实现业务处理
            @Override
            public void execute() {
                System.out.println("--------------Anonymous Inner Class----------------");
            }
        });
//        new TestBookCallBack().test(()->new CallBack(){
//            @Override
//            public void execute() {
//                System.out.println("--------------Anonymous Inner Class");
//            }
//        });
        //处理完业务后执行回调
        var testBookCallBack2 = new TestBookCallBack();
        var bookCallBack = new BookCallBack();
        testBookCallBack2.test(bookCallBack);//进行业务处理
        System.out.println("--------main--------中获取callback实现类里定义的变量值: ");//进行回调
        System.out.println(bookCallBack.getAuthor() + " " + bookCallBack.getBookName());
    }
}
//业务处理类
class TestBookCallBack {
    public void test(CallBack callBack){
        System.out.println("-------业务处理类------");
        callBack.execute();
    }
}
//回调类
class BookCallBack implements CallBack{
    private String bookName;
    private String author;

    public String getBookName() {
        return bookName;
    }
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public void execute() {
        this.bookName = "The little prince";
        this.author = "Antoine";
        System.out.println("---------callback实现类 bookName=="+ this.bookName +"      author=="+this.author + "--------------");
    }

}

