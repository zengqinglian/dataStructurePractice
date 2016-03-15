import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadDeadlock
{
    ExecutorService exec = Executors.newSingleThreadExecutor();

    public class RenderPageTask implements Callable<String>
    {
        public String call() throws Exception {
            Future<String> header, footer;
            header = exec.submit( new Callable<String>() {
                public String call() {
                    return "header";
                }
            } );
            footer = exec.submit( new Callable<String>() {
                public String call() {
                    return "footer";
                }
            } );
            String page = "page";
            // Will deadlock -- task waiting for result of subtask
            return header.get() + page + footer.get();
        }
    }

    public static void main( String[] args ) {

    }
}