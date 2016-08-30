//
//  main.c
//  merge
//
//  Created by 厳恒 on 2016/08/11.
//  Copyright © 2016年 厳恒. All rights reserved.
//

#include <stdio.h>

void merge(int *a,int asize,int *b,int bsize);

int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, merge!\n");
    const int asize=15;
    int a[asize]= {1,3,5,7,9};
    int b[] = {2,4,6,8,10,23,34,56};
    int i;
    int bsize=sizeof(b)/sizeof(int);
    //merge
    merge(a,asize,b,bsize);
    for(i=0;i<15;i++){
        printf("a[%d]=%d \n",i,a[i]);
    }
    return 0;
}
void merge(int *a,int asize,int *b,int bsize)
{
    int a_index=asize-1;
    int b_index=bsize-1;
    int set_index;
    //find a start
    while(a[a_index]==0){
        a_index--;
    }
    set_index=a_index+b_index+1;
    printf("aindex == %d ,, a[%d] == %d \n",a_index,a_index,a[a_index]);
    while(set_index>=0){
        if(a[a_index]>b[b_index]){
            a[set_index]=a[a_index];
            a_index--;
        }else{
            a[set_index]=b[b_index];
            b_index--;
        }
        set_index--;
    }
}