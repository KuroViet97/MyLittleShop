package Camera;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TestMultipleScan {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		GrabberShowUsesCallable gs = new GrabberShowUsesCallable();
		//GrabberShow gs = new GrabberShow();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        String code[] = new String[10];
        int x = 0;
        while (x < 10) {
        	Future<String> future = executorService.submit(gs);
        	code[x] = future.get();
        	if ( (x >= 1) && (code[x].equals(code[x-1])) ) {
        		x--;
        	}
        	x++;
        }
        for(int i = 0; i < 10; i++) {
        	System.out.println(i + "." + code[i]);
        }
    }
}
