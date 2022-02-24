#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

char* convert (int n) {
    char* to_ret = (char *) malloc (101);
    sprintf(to_ret,"%d",n);
    return to_ret;
}
char* convertF (float n) {
    char* to_ret = (char *) malloc (101);
    sprintf(to_ret,"%f",n);
    return to_ret;
}
char* convertS (char * s) {
    char* to_ret = (char *) malloc (101);
    sprintf(to_ret,"%s",s);
    return to_ret;
}
int power(int a, int b) {
int i = 0;
int res =1;
while(i<b) {
res = res*a;
i = i+1;
}
return res;
 }
int i;
int a = 1;int b = 2;

int main () {
switch  (i) {
case 1:
a = a+1;
 break;
case 2:
a = a+1;
 break;
case 3:
a = a+1;
 break;
}
;
return 0;}
