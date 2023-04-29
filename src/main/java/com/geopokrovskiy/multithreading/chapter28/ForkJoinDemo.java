package com.geopokrovskiy.multithreading.chapter28;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class SqrtTransform extends RecursiveAction{
    final int seqThreshold = 1000;
    double[] data ;
    int start, end;

    SqrtTransform(double[] vals, int s, int e){
        data = vals;
        start = s;
        end = e;
    }
    @Override
    protected void compute() {
        if((end - start) < seqThreshold){
            for(int i = start; i < end; i++){
                data[i] = Math.sqrt(data[i]);
            }
        } else {
            int middle = (start + end) / 2;
            invokeAll(new SqrtTransform(data, start, middle),
                    new SqrtTransform(data, middle, end));
        }
    }
}
public class ForkJoinDemo {
    public static void main(String[] args) {
        ForkJoinPool fjp = new ForkJoinPool();
        double[] nums = new double[100000];

        for(int i = 0; i < nums.length; i++){
            nums[i] = (double) i;
        }

        System.out.println("Initial sequence: ");
        for(int i = 0; i < 10; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println("\n");

        SqrtTransform task = new SqrtTransform(nums, 0, nums.length);
        fjp.invoke(task);

        System.out.println("Part of the new sequence");
        for(int i = 0; i < 10; i++){
            System.out.format("%.4f ", nums[i]);
        }
        System.out.println();
    }

}
