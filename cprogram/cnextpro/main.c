//
//  main.c
//  cnextpro
//
//  Created by 厳恒 on 2016/08/08.
//  Copyright © 2016年 厳恒. All rights reserved.
//

#include <stdio.h>

int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, Worlssssd!\n");
    int num=0;
    /*
     *scanf 是一个阻塞式函数
     cpu会等待用户输入数据，在
     */
    scanf("%d",&num);
    
    printf("num== %d \n ",num);
    return 0;
}
