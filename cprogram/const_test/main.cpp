//
//  main.cpp
//  const_test
//
//  Created by 厳恒 on 2016/08/21.
//  Copyright © 2016年 厳恒. All rights reserved.
//
/*
 http://edu.csdn.net/course/detail/885/12315?auto_start=1
 最适合自学的C++基础视频
 16. 16_const的基础和const符号表机制探究 (免费)
 */

#include <iostream>

void change_value_1(const int *a,int * const b,const int * const c);

void change_value_2(const int *a)
{
    
}

int main(int argc, const char * argv[]) {
    // insert code here...
    std::cout << "Hello, World!\n";
    //init
    int a=10,b=20,c=30;
    
    change_value_1(&a,&b,&c);
    
    printf("a=%d,\nb=%d,\nc=%d\n",(int *)&a,(int *)&b,(int *)&c);
    printf("a=%d,\nb=%d,\nc=%d\n",a,b,c);
    printf("test 2----");
    const int a2=10;
    const int b2=20;
    const int c2=30;
    
    change_value_2(&a2);
    
    return 0;
}
void change_value_1(const int *a,int * const b,const int * const c)
{
//    *a=100;
    *b=200;
//    *c=300;
    
    int *p;
//    p=a;
    p=b;
//    p=c;
//    b=a;
}

