; Reversing array in place

; Initialize array data in memory
ldc R0 2        ; Base address
ldc R1 0
str R0 R1       ; mem[0] = 2 (base address)

ldc R0 5        ; Length
ldc R1 1
str R0 R1       ; mem[1] = 5 (length)

; Initialize array values
ldc R0 10
ldc R1 2
str R0 R1       ; mem[2] = 10

ldc R0 20
ldc R1 3
str R0 R1       ; mem[3] = 20

ldc R0 30
ldc R1 4
str R0 R1       ; mem[4] = 30

ldc R0 40
ldc R1 5
str R0 R1       ; mem[5] = 40

ldc R0 50
ldc R1 6
str R0 R1       ; mem[6] = 50

; Now reverse the array
ldc R0 0
ldr R0 R0       ; R0 = base address (2)

ldc R1 1
ldr R1 R1       ; R1 = length (5)

; Calculate end index
cpy R2 R0
add R2 R1
ldc R3 1
sub R2 R3       ; R2 = end index (6)

reverse_loop:
    ; Check if left >= right
    cpy R3 R0
    sub R3 R2
    je R3 @done

    ; Swap arr[R0] and arr[R2]
    ldc R3 60
    ldr R1 R0
    str R1 R3       ; temp = arr[left]

    ldr R1 R2
    str R1 R0       ; arr[left] = arr[right]

    ldr R1 R3
    str R1 R2       ; arr[right] = temp

    ; left++, right--
    ldc R3 1
    add R0 R3
    sub R2 R3

    ; Continue if left < right
    cpy R3 R0
    sub R3 R2
    jne R3 @reverse_loop

done:
; Print reversed array to verify
ldc R0 2
prm R0          ; Print mem[2] - should be 50

ldc R0 3
prm R0          ; Print mem[3] - should be 40

ldc R0 4
prm R0          ; Print mem[4] - should be 30

ldc R0 5
prm R0          ; Print mem[5] - should be 20

ldc R0 6
prm R0          ; Print mem[6] - should be 10

hlt
