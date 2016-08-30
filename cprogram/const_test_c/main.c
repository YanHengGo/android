//
//  main.c
//  const_test_c
//
//  Created by 厳恒 on 2016/08/21.
//  Copyright © 2016年 厳恒. All rights reserved.
//

#include <stdio.h>

void funca()
{
#define FUNC_A 10
    
}
void funcb()
{
    printf("func_a=%d \n",FUNC_A);
}
int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, World!\n");
    
    const int a=10;
    int *ap;
    ap=&a;
    *ap=20;
    
    printf("*ap=%d \n ,ap=%d \n ",*ap,ap);
    printf("a=%d \n &a=%d \n ",a,&a);
    
    printf("---------------- \n");
//    funca();
    funcb();
    return 0;
}
