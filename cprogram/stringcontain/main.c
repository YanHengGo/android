//
//  main.c
//  stringcontain
//
//  Created by 厳恒 on 2016/08/16.
//  Copyright © 2016年 厳恒. All rights reserved.
//  1.2 字符串包含

#include <stdio.h>

int string_contain(char *base,char *tar);
int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, string contain!\n");
    char base[]={"fjskleruoiejfkls"};
    char tar[]={"flera"};
    int ret ;
    
    ret=string_contain(base,tar);
    
    printf("result is %d \n",ret);
    
    return 0;
}

int string_contain(char *base,char *tar)
{
    char contain[128]={0};
    int base_length=0,tar_length=0,i;
    //init
    while(*(base+base_length)!='\0')base_length++;
    while(*(tar+tar_length)!='\0')tar_length++;
    
    //set tar to contain
    for(i=0;i<tar_length;i++){
        contain[tar[i]]=1;
    }
    //delete contain
    for(i=0;i<base_length;i++){
        contain[base[i]]=0;
    }
    for(i=0;i<128;i++){
        if(contain[i]!=0)return -1;
    }
    return 0;
}

