//
//  main.m
//  findSumOC
//
//  Created by 厳恒 on 2016/08/17.
//  Copyright © 2016年 厳恒. All rights reserved.
//

#import <Foundation/Foundation.h>

void towSum(int data[],int sum,int left,int length);

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        // insert code here...
        NSLog(@"Hello, World!");
        printf("hello printf \n");
    }
    int data[] = {1,2,4,7,9,11,15,19,21,23,41};
    int sum=30;
    towSum(data, sum,0,11);
    return 0;
}

void towSum(int data[],int sum,int left,int length)
{
    int right,tmp;
    //init
    right=length-1;
    while(left<right){
        tmp=data[left]+data[right];
        if(tmp==sum){
            printf("data[%d]=%d ,, data[%d]=%d  \n",left,data[left],right,data[right]);
            towSum(data, sum, ++left, length);
            break;
        }else{
            if(tmp>sum)right--;
            if(tmp<sum)left++;
        }
    }
}

