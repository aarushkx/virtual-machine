; Store values 0, 1, 2 into memory starting at address 20
; Then print the final counter value (should print 3)

ldc R0 0          ; R0 = counter (i = 0)
ldc R1 3          ; R1 = limit (stop when i == 3)
ldc R2 20         ; R2 = memory pointer (start at address 20)

loop:
    str R0 R2     ; memory[R2] = R0

    ldc R3 1      ; R3 = 1 (increment value)

    add R0 R3     ; i++
    add R2 R3     ; move pointer to next memory cell

    cpy R3 R1     ; R3 = limit
    sub R3 R0     ; R3 = limit - i

    jne R3 @loop  ; if (limit - i) != 0, continue loop

; After loop exits (i == 3)
prr R0            ; print final value of counter (3)
hlt
