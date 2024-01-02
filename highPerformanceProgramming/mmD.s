	.file	"ex-mm-daxpy.c"
	.text
	.p2align 4
	.globl	matmul
	.type	matmul, @function
matmul:
.LFB0:
	.cfi_startproc
	endbr64
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	pushq	%r15
	pushq	%r14
	pushq	%r13
	pushq	%r12
	pushq	%rbx
	andq	$-32, %rsp
	subq	$96, %rsp
	.cfi_offset 15, -24
	.cfi_offset 14, -32
	.cfi_offset 13, -40
	.cfi_offset 12, -48
	.cfi_offset 3, -56
	movq	%rsi, 32(%rsp)
	testq	%rcx, %rcx
	jle	.L31
	movq	%rcx, %r15
	leaq	0(,%rcx,8), %rax
	movq	%rdi, %r14
	movq	%rdi, %rcx
	movq	%rax, 88(%rsp)
	leaq	(%rsi,%rax), %rdi
	leaq	(%rax,%r14), %rbx
	xorl	%r8d, %r8d
	leaq	-1(%r15), %rax
	movq	%rdi, 80(%rsp)
	movq	%rdx, %r13
	xorl	%r12d, %r12d
	movq	%rax, 64(%rsp)
	movq	%r15, %rax
	shrq	$2, %rax
	movq	%r8, 48(%rsp)
	salq	$5, %rax
	movq	%rax, %r11
	movq	%r15, %rax
	andq	$-4, %rax
	movq	%r11, 24(%rsp)
	movq	%rax, 56(%rsp)
	movq	%r15, %rax
	andl	$3, %eax
	movq	%rax, 40(%rsp)
	.p2align 4,,10
	.p2align 3
.L3:
	movq	88(%rsp), %rdx
	movq	%rcx, %rdi
	xorl	%esi, %esi
	call	memset@PLT
	movq	24(%rsp), %r11
	movq	%r13, %rsi
	xorl	%edi, %edi
	movq	%rax, %rcx
	movq	32(%rsp), %rax
	leaq	(%rax,%r12,8), %rax
	.p2align 4,,10
	.p2align 3
.L16:
	cmpq	$1, %r15
	je	.L19
	leaq	8(%rax), %r8
	cmpq	%r8, %rcx
	setnb	%r9b
	cmpq	%rbx, %rax
	setnb	%dl
	orb	%r9b, %dl
	je	.L20
	leaq	8(%rsi), %r9
	movq	%rcx, %rdx
	subq	%r9, %rdx
	cmpq	$16, %rdx
	jbe	.L20
	cmpq	$2, 64(%rsp)
	jbe	.L18
	vbroadcastsd	(%rax), %ymm1
	xorl	%edx, %edx
	.p2align 4,,10
	.p2align 3
.L10:
	vmovupd	(%rsi,%rdx), %ymm0
	vfmadd213pd	(%rcx,%rdx), %ymm1, %ymm0
	vmovupd	%ymm0, (%rcx,%rdx)
	addq	$32, %rdx
	cmpq	%rdx, %r11
	jne	.L10
	movq	56(%rsp), %r10
	cmpq	%r10, %r15
	je	.L8
	movq	40(%rsp), %r9
	movq	%r9, %rdx
	cmpq	$1, %r9
	je	.L17
	movq	%r10, 72(%rsp)
	movq	%r10, %r9
.L9:
	leaq	(%r9,%r12), %r10
	addq	%rdi, %r9
	vmovddup	(%rax), %xmm0
	leaq	(%r14,%r10,8), %r10
	vmovupd	(%r10), %xmm4
	vfmadd132pd	0(%r13,%r9,8), %xmm4, %xmm0
	vmovupd	%xmm0, (%r10)
	testb	$1, %dl
	je	.L8
	movq	72(%rsp), %r10
	andq	$-2, %rdx
	addq	%r10, %rdx
.L5:
	leaq	(%r12,%rdx), %r9
	vmovsd	(%rax), %xmm0
	addq	%rdi, %rdx
	leaq	(%r14,%r9,8), %r9
	vmovsd	(%r9), %xmm3
	vfmadd132sd	0(%r13,%rdx,8), %xmm3, %xmm0
	vmovsd	%xmm0, (%r9)
.L8:
	movq	88(%rsp), %rax
	addq	%r15, %rdi
	addq	%rax, %rsi
	movq	%r8, %rax
	cmpq	%r8, 80(%rsp)
	jne	.L16
	incq	48(%rsp)
	movq	88(%rsp), %rdi
	addq	%r15, %r12
	addq	%rdi, 80(%rsp)
	addq	%rdi, %rcx
	addq	%rdi, %rbx
	movq	48(%rsp), %rax
	cmpq	%rax, %r15
	je	.L30
	vzeroupper
	jmp	.L3
	.p2align 4,,10
	.p2align 3
.L20:
	movq	%rsi, %r9
	movq	%rcx, %rdx
	.p2align 4,,10
	.p2align 3
.L7:
	vmovsd	(%r9), %xmm0
	vmovsd	(%rdx), %xmm2
	addq	$8, %rdx
	addq	$8, %r9
	vfmadd132sd	(%rax), %xmm2, %xmm0
	vmovsd	%xmm0, -8(%rdx)
	cmpq	%rbx, %rdx
	jne	.L7
	jmp	.L8
	.p2align 4,,10
	.p2align 3
.L19:
	movq	%rsi, %r9
	movq	%rcx, %rdx
	leaq	8(%rax), %r8
	jmp	.L7
.L18:
	movq	$0, 72(%rsp)
	movq	%r15, %rdx
	xorl	%r9d, %r9d
	jmp	.L9
.L17:
	movq	%r10, %rdx
	jmp	.L5
.L30:
	vzeroupper
.L31:
	leaq	-40(%rbp), %rsp
	popq	%rbx
	popq	%r12
	popq	%r13
	popq	%r14
	popq	%r15
	popq	%rbp
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE0:
	.size	matmul, .-matmul
	.ident	"GCC: (Ubuntu 13.2.0-4ubuntu3) 13.2.0"
	.section	.note.GNU-stack,"",@progbits
	.section	.note.gnu.property,"a"
	.align 8
	.long	1f - 0f
	.long	4f - 1f
	.long	5
0:
	.string	"GNU"
1:
	.align 8
	.long	0xc0000002
	.long	3f - 2f
2:
	.long	0x3
3:
	.align 8
4:
