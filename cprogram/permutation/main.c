//
//  main.c
//  permutation
//
//  Created by 厳恒 on 2016/08/10.
//  Copyright © 2016年 厳恒. All rights reserved.
//

#include <stdio.h>

char *permu(char *a,int length);
int main(int argc, const char * argv[]) {
    printf("Hello, World!\n");
    char a[]={"abcd"};
    int length =0;
    //calculater
    for(length=0;a[length]!='\0';length++){
        ;
    }
    
    permu(a,length);
    
    return 0;
}
char *permu(char *a,int length)
{
    char result[length];
    char *base;
    int i,j;
    
    if(length == 1){
        return a;
    }
    base = permu(a+1,length-1);
    memcpy(result,base,length-1);
    printf("result = %s  base == %s ,, length = %d ,, a = %s \n",result,base,length,a);
    for(i=0;i<length;i++){
        for(j=length;j>=i;j--){
            if(j==i){
                result[j]= *a;
            }
            result[j]=result[j-1];
        }
    }
    return result;
}
