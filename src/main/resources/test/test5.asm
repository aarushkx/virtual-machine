; Print A-Z using a loop

ldc R0 65           ; R0 = current character ('A')
ldc R1 90           ; R1 = end character ('Z')

loop:
    prc R0          ; Print current character

    ldc R2 1
    add R0 R2       ; Increment character

    ; Check if we've passed 'Z'
    cpy R2 R0       ; R2 = current char
    ldc R3 91       ; R3 = 'Z' + 1
    sub R2 R3       ; R2 = current - 91

    ; If R2 != 0, we haven't reached Z+1 yet, continue
    jne R2 @loop

ldc R2 10           ; Newline ('\n')
prc R2

hlt
