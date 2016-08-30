//
//  main.c
//  wordcontain
//
//  Created by 厳恒 on 2016/08/16.
//  Copyright © 2016年 厳恒. All rights reserved.
//

#include <stdio.h>
#define WORD_LENGTH 20
int wordcontain(char (*dic)[WORD_LENGTH],char *word);
int main(int argc, const char * argv[]) {
    // insert code here...
    printf("Hello, World!\n");
    
    char dic[][WORD_LENGTH]={
        {"add"},
        {"app"},
        {"apple"},
        {"banana"},
    };
    char word[]={"app"};
    int ret ;
    ret = wordcontain(dic, word);
    printf("result ret is %d \n",ret);
    return 0;
}
int wordcontain(char (*dic)[WORD_LENGTH],char *word)
{
    
    return -1;
}
