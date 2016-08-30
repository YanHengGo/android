//
//  main.c
//  search
//
//  Created by 厳恒 on 2016/08/11.
//  Copyright © 2016年 厳恒. All rights reserved.
//  9.3 在一个数字中查找特定元素

#include <stdio.h>

int search(int *base,int start,int end,int tar);

int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, search!\n");
    int index;
    int a[12] = {
        15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14
    };
//    int a[19] = {
//        2,2,2,2,2,2,2,2,3,2,2,2,2,2,2,2,2,2,2
//    };
    int search_value=14;
    index=search(a,0,sizeof(a)/sizeof(a[0])-1,search_value);
    
    printf("finished to find %d --> a[%d] = %d \n",search_value,index,a[index]);
    
    return 0;
}
int search(int *base,int start,int end,int tar)
{
    int mid;
    while(start<end){
        mid=(end+start)/2;
        printf("start = %d ,,end = %d ,, mid = %d \n",start,end,mid);
        if(*(base+mid)==tar)return mid;
        if(*(base+start)==tar)return start;
        if(*(base+end)==tar)return end;
        
        if(*(base+start)<*(base+mid)){
            if(*(base+start)<=tar&&tar<=*(base+mid)){
                end=mid;
            }else{
                start=mid;
            }
        }else{
            if(*(base+mid)<=tar&&tar<=*(base+end)){
                start=mid;
            }else{
                end=mid;
            }
        }
    }
    return -1;
}

