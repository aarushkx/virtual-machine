; Sum of numbers from 1 to 5

ldc R0 0            ; R0 = sum
ldc R1 1            ; R1 = counter (i = 1)
ldc R2 6            ; R2 = limit (stop when i == 6)

loop:
    add R0 R1       ; sum += i

    ldc R3 1
    add R1 R3       ; i++

    cpy R3 R2
    sub R3 R1       ; R3 = limit - i

    jne R3 @loop    ; if R3 != 0, continue loop

prr R0              ; print sum (should be 15)
hlt
