//
//  main.cpp
//  findSum
//
//  Created by 厳恒 on 2016/08/17.
//  Copyright © 2016年 厳恒. All rights reserved.
//

#include <iostream>

void TwoSum(int data[], unsigned int length, int sum);

int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    int sum = 3;
    int data[]={1,2,4,7,9,11,15};
    
    TwoSum(data, 6, sum);
    
    
    return 0;
}

void TwoSum(int data[], unsigned int length, int sum)
{
    //sort(s, s+n);   如果数组非有序的，那就事先排好序O(N log N)
    
    int begin = 0;
    int end = length - 1;
    
    //俩头夹逼，或称两个指针两端扫描法，很经典的方法，O(N)
    while (begin < end)
    {
        long currSum = data[begin] + data[end];
        
        if (currSum == sum)
        {
            //题目只要求输出满足条件的任意一对即可
            printf("%d %d\n", data[begin], data[end]);
            
            //如果需要所有满足条件的数组对，则需要加上下面两条语句：
            //begin++
            //end--
            break;
        }
        else{
            if (currSum < sum)
            begin++;
            else
            end--;
        }
    }
}