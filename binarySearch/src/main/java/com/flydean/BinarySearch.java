package com.flydean;

public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = new int[]{1,3,3,3,5};
        BinarySearch bs = new BinarySearch();
       System.out.println(bs.searchInsertLeftMost(nums, 2));
        System.out.println(bs.searchInsertRightMost(nums, 3));
    }

    public int searchInsertLeftMost(int[] nums, int target) {
        int left=0;
        int right = nums.length-1;
        while(left <=right){
            int mid=(left+right)/2;
            if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }
        System.out.println(left+":"+right);
        return left;
    }

    public int searchInsertRightMost(int[] nums, int target) {
        int left=0;
        int right = nums.length-1;
        while(left <=right){
            int mid=(left+right)/2;
            if(nums[mid]<=target){
                left=mid+1;
            }else{
                right=mid-1;
            }

        }
        System.out.println(left+":"+right);
        return right;
    }


}
