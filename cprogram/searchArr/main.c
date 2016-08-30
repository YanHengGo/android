//
//  main.c
//  searchArr
//
//  Created by 厳恒 on 2016/08/11.
//  Copyright © 2016年 厳恒. All rights reserved.
//9.5 写一个函数找到给定字符串的位置
/*
 *
 例子：在字符串数组 [“at”, “”, “”, “”, “ball”, “”, “”, “car”, “”,“”, “dad”, “”, “”] 中找到”ball”，返回下标4.
 
 例子：在字符串数组 [“at”, “”, “”, “”, “”, “ball”, “car”, “”, “”, “dad”, “”, “”] 中找到”ballcar”，查找失败，返回-1.
 
 */
#include <stdio.h>
#define STR_SIZE 10


int searcharr(char (*base)[STR_SIZE] , char *tar,int size);

int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, World!\n");
    char a[][STR_SIZE]={
        {"at"},
        {""},
        {""},
        {""},
        {"ball"},
        {""},
        {""},
        {"car"},
        {""},
        {""},
        {"dad"},
        {""},
        {"eat"}
    };
    int index ;
    char tar[STR_SIZE]={"eat"};
    int size=sizeof(a)/sizeof(a[0]);
    index = searcharr(a, tar,size);
    printf("index == %d \n",index);
    
    return 0;
}
int searcharr(char (*base)[STR_SIZE] , char *tar,int size)
{
    int start=0;
    int end=size-1;
    int mid;
    int skip,cmp;
    
    while(start<end){
        skip=0;
        mid=(start+end)/2;
        while(skip<end){
            if(base[mid+skip][0]!=0){
                mid=mid+skip;
                break;
            }
            if(base[mid-skip][0]!=0){
                mid=mid-skip;
                break;
            }
            skip++;
        }
        cmp = strcmp(base[mid],tar);
        
        if(cmp==0){
            return mid;
        }else if(cmp>0){
            end=mid-1;
        }else if(cmp<0){
            start=mid+1;
        }
        printf("cmp==%d mid==%d ,, start==%d ,, end==%d\n",cmp,mid,start,end);
    }
    return -1;
}
