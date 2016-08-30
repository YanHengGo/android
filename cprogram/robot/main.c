//
//  main.c
//  robot
//
//  Created by 厳恒 on 2016/08/10.
//  Copyright © 2016年 厳恒. All rights reserved.
//

#include <stdio.h>
#define MAX_TOP 4
#define MAX_RIGHT 4

int cnt =0;
void walk(int step,int currentT,int currentR);
int main(int argc, const char * argv[]) {
    printf("Hello, World!\n");
    
    walk(MAX_TOP+MAX_RIGHT,0,0);
    
    printf("finished cnt=%d \n",cnt);
    
    return 0;
}
void walk(int step,int currentT,int currentR)
{
    if(step==0){
        cnt++;
        return;
    }
    if(currentT<MAX_TOP){
        walk(step-1, currentT+1, currentR);
    }
    if(currentR<MAX_RIGHT){
        walk(step-1, currentT, currentR+1);
    }
}