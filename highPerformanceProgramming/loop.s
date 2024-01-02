	.file	"loop.c"
	.text
	.p2align 4
	.globl	loop
	.type	loop, @function
loop:
.LFB50:
	.cfi_startproc
	endbr64
	movq	%rcx, %r10
	leaq	(%rcx,%rcx,2), %r9
	salq	$6, %r10
	salq	$5, %r9
	testq	%rsi, %rsi
	je	.L7
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	imulq	%rcx, %rdx
	movq	%rcx, %r11
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	pushq	%r12
	vpxor	%xmm2, %xmm2, %xmm2
	movq	%rdx, %r8
	pushq	%rbx
	.cfi_offset 12, -24
	.cfi_offset 3, -32
	salq	$7, %r11
	leaq	0(,%rcx,4), %rbx
	xorl	%r12d, %r12d
	salq	$3, %rbx
	salq	$5, %rcx
	vmovdqa	%ymm2, %ymm1
	.p2align 5
	.p2align 3
.L3:
	movq	%rdi, %rax
	xorl	%edx, %edx
	testq	%r8, %r8
	je	.L6
	.p2align 5
	.p2align 3
.L4:
	vpxor	(%rax), %ymm1, %ymm1
	vpxor	(%rax,%rcx), %ymm2, %ymm2
	vpxor	(%rax,%r10), %ymm1, %ymm1
	vpxor	(%rax,%r9), %ymm2, %ymm2
	addq	%r11, %rax
	vpxor	(%rax), %ymm1, %ymm1
	vpxor	(%rax,%rcx), %ymm2, %ymm2
	vpxor	(%rax,%r10), %ymm1, %ymm1
	vpxor	(%rax,%r9), %ymm2, %ymm2
	addq	%r11, %rax
	vpxor	(%rax), %ymm1, %ymm1
	vpxor	(%rax,%rcx), %ymm2, %ymm2
	vpxor	(%rax,%r10), %ymm1, %ymm1
	vpxor	(%rax,%r9), %ymm2, %ymm2
	addq	%r11, %rax
	vpxor	(%rax), %ymm1, %ymm1
	vpxor	(%rax,%rcx), %ymm2, %ymm2
	vpxor	(%rax,%r10), %ymm1, %ymm1
	vpxor	(%rax,%r9), %ymm2, %ymm2
	addq	%r11, %rax
	vpxor	(%rax), %ymm1, %ymm1
	vpxor	(%rax,%rcx), %ymm2, %ymm2
	vpxor	(%rax,%r10), %ymm1, %ymm1
	vpxor	(%rax,%r9), %ymm2, %ymm2
	addq	%r11, %rax
	vpxor	(%rax), %ymm1, %ymm1
	vpxor	(%rax,%rcx), %ymm2, %ymm2
	vpxor	(%rax,%r10), %ymm1, %ymm1
	vpxor	(%rax,%r9), %ymm2, %ymm2
	addq	%r11, %rax
	vpxor	(%rax), %ymm1, %ymm1
	vpxor	(%rax,%rcx), %ymm2, %ymm2
	vpxor	(%rax,%r10), %ymm1, %ymm1
	vpxor	(%rax,%r9), %ymm2, %ymm2
	addq	%r11, %rax
	vpxor	(%rax), %ymm1, %ymm1
	vpxor	(%rax,%rcx), %ymm2, %ymm2
	vpxor	(%rax,%r10), %ymm1, %ymm1
	addq	%rbx, %rdx
	vpxor	(%rax,%r9), %ymm2, %ymm2
	addq	%r11, %rax
	cmpq	%r8, %rdx
	jb	.L4
.L6:
	incq	%r12
	cmpq	%r12, %rsi
	jne	.L3
	popq	%rbx
	popq	%r12
	vpxor	%ymm2, %ymm1, %ymm0
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
.L7:
	.cfi_restore 3
	.cfi_restore 6
	.cfi_restore 12
	vpxor	%xmm0, %xmm0, %xmm0
	ret
	.cfi_endproc
.LFE50:
	.size	loop, .-loop
	.ident	"GCC: (Ubuntu 9.4.0-1ubuntu1~20.04.2) 9.4.0"
	.section	.note.GNU-stack,"",@progbits
	.section	.note.gnu.property,"a"
	.align 8
	.long	 1f - 0f
	.long	 4f - 1f
	.long	 5
0:
	.string	 "GNU"
1:
	.align 8
	.long	 0xc0000002
	.long	 3f - 2f
2:
	.long	 0x3
3:
	.align 8
4:
