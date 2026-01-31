ldc R0 0
ldc R1 3
ldc R2 20
loop:
str R0 R2
ldc R3 1
add R0 R3
add R2 R3
cpy R3 R1
sub R3 R0
jne R3 @loop
prr R0
hlt