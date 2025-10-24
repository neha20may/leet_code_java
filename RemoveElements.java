//Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
//
//Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
//
//Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
//Return k.

public class RemoveElements {
    //remove val
    //remove k = count(not val)
//    [0,1,2,2,3,0,4,2]
    //val =2
    //[0,1,3,0,4]
    //k=5
    //[0,1,2,2,3,0,4,2] traverse and two pointer
    // [0,1,2,2,3,0,4,2] i=n j=n-1
    //j move on i at the boundary of val after i remains the no =n val
    //while j>=0
    // {if a[j]==val
    // swap(--i, j);
    //j--}
    //that way all 2 wills be at the end and you remain with the boundary ; oh god boundary technique is so good.

}


