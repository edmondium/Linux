	.file	"ex-mm-block.c"
	.text
	.p2align 4
	.globl	matmul
	.type	matmul, @function
matmul:
.LFB15:
	.cfi_startproc
	endbr64
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	leaq	3(%rcx), %rax
	movq	%rax, %r10
	andq	$-4, %r10
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	pushq	%r15
	.cfi_offset 15, -24
	movq	%rdx, %r15
	pushq	%r14
	pushq	%r13
	pushq	%r12
	pushq	%rbx
	.cfi_offset 14, -32
	.cfi_offset 13, -40
	.cfi_offset 12, -48
	.cfi_offset 3, -56
	movq	%rcx, %rbx
	andq	$-32, %rsp
	subq	$320, %rsp
	movq	%rcx, 288(%rsp)
	sarq	$63, %rcx
	movq	%rcx, %rax
	movq	%rsi, 24(%rsp)
	shrq	$62, %rax
	movq	%r10, 56(%rsp)
	leaq	(%rbx,%rax), %rdx
	andl	$3, %edx
	subq	%rax, %rdx
	testq	%rbx, %rbx
	jle	.L39
	movq	%rbx, %rcx
	movq	%rbx, %rax
	movq	%rsi, %r11
	movq	%r10, %rsi
	subq	%rdx, %rcx
	movq	%rdi, %r14
	salq	$4, %r10
	movq	%rdi, %r8
	movq	%rcx, %rbx
	movq	%rcx, 104(%rsp)
	movq	%rax, %rcx
	leaq	(%rsi,%rsi), %rdi
	salq	$4, %rcx
	movq	%r10, 144(%rsp)
	movq	%rcx, 80(%rsp)
	leaq	(%r11,%rbx,8), %rcx
	movq	%rcx, 112(%rsp)
	movq	%rax, %rcx
	leaq	(%rax,%rax), %rax
	movq	%rax, 88(%rsp)
	leaq	-1(%rcx), %rax
	movq	%rax, 200(%rsp)
	andq	$-2, %rax
	addq	$2, %rax
	movq	%rdi, 128(%rsp)
	movq	%rax, 96(%rsp)
	leaq	0(,%rsi,8), %rax
	movq	%rax, 216(%rsp)
	movq	%rsi, %rax
	salq	$5, %rsi
	movq	%rsi, 176(%rsp)
	leaq	(%r15,%r10), %rsi
	movq	%rsi, 64(%rsp)
	movq	%rax, %rsi
	leaq	0(,%rax,4), %rax
	movq	%rax, 168(%rsp)
	leaq	(%rdi,%rsi), %rax
	movq	%rax, 16(%rsp)
	movq	%rcx, %rax
	shrq	$2, %rax
	movq	%rsi, 296(%rsp)
	salq	$5, %rax
	movq	%r11, 136(%rsp)
	movq	%rax, %r13
	movq	%rcx, %rax
	leaq	0(,%rcx,8), %rcx
	movq	$0, 304(%rsp)
	imulq	%rax, %rbx
	movq	%rcx, 40(%rsp)
	movq	$0, 120(%rsp)
	movq	$0, 152(%rsp)
	movq	%rbx, 48(%rsp)
	leaq	0(,%rdx,8), %rbx
	movq	%rbx, 32(%rsp)
	movq	%rax, %rbx
	andl	$3, %ebx
	movq	%r13, 208(%rsp)
	movq	%rbx, 160(%rsp)
	movq	%rax, %rbx
	andq	$-2, %rax
	movq	%rax, 184(%rsp)
	andq	$-4, %rbx
	movq	%rbx, 256(%rsp)
	movq	%r14, %rbx
	.p2align 4,,10
	.p2align 3
.L19:
	movq	144(%rsp), %rdx
	xorl	%esi, %esi
	movq	%r8, %rdi
	call	memset@PLT
	cmpq	$3, 288(%rsp)
	movq	%rax, %r8
	jle	.L11
	movq	136(%rsp), %rsi
	movq	216(%rsp), %rdx
	movq	%rbx, 72(%rsp)
	movq	%r15, %r10
	movq	304(%rsp), %rdi
	movq	24(%rsp), %rcx
	leaq	(%rsi,%rdx), %rax
	movq	56(%rsp), %r11
	movq	16(%rsp), %r12
	movq	%rsi, 280(%rsp)
	movq	256(%rsp), %rsi
	movq	128(%rsp), %r13
	movq	%rax, 264(%rsp)
	movq	288(%rsp), %rax
	movq	64(%rsp), %r9
	subq	$4, %rax
	andq	$-4, %rax
	addq	%rdi, %rax
	leaq	32(%rcx,%rax,8), %rax
	leaq	(%r8,%rdx), %rcx
	xorl	%edx, %edx
	movq	%rax, 192(%rsp)
	leaq	(%rdi,%rsi), %rax
	movq	%rdx, %rbx
	movq	%r11, %rdx
	leaq	(%r14,%rax,8), %rax
	movq	%rcx, 272(%rsp)
	movq	%rax, 240(%rsp)
	movq	296(%rsp), %rax
	addq	%rsi, %rax
	leaq	(%r14,%rax,8), %rax
	movq	%rax, 232(%rsp)
	movq	160(%rsp), %rax
	andl	$1, %eax
	movq	%rax, 224(%rsp)
	.p2align 4,,10
	.p2align 3
.L10:
	movq	280(%rsp), %rax
	movq	208(%rsp), %r11
	movq	272(%rsp), %rcx
	vmovsd	24(%rax), %xmm3
	vmovsd	(%rax), %xmm0
	vmovsd	8(%rax), %xmm7
	vmovsd	16(%rax), %xmm5
	movq	264(%rsp), %rax
	vbroadcastsd	%xmm3, %ymm9
	vbroadcastsd	%xmm0, %ymm15
	vbroadcastsd	%xmm7, %ymm13
	vbroadcastsd	%xmm5, %ymm11
	vmovsd	16(%rax), %xmm4
	vmovsd	24(%rax), %xmm2
	vmovsd	(%rax), %xmm1
	vmovsd	8(%rax), %xmm6
	movq	216(%rsp), %rax
	vbroadcastsd	%xmm4, %ymm10
	vbroadcastsd	%xmm2, %ymm8
	vmovsd	%xmm4, 248(%rsp)
	vbroadcastsd	%xmm1, %ymm14
	vbroadcastsd	%xmm6, %ymm12
	vmovsd	%xmm3, %xmm3, %xmm4
	vmovsd	%xmm2, 312(%rsp)
	leaq	(%r10,%rax), %rdi
	leaq	(%r9,%rax), %rsi
	vmovapd	%ymm15, %ymm3
	xorl	%eax, %eax
	vmovapd	%ymm14, %ymm15
	vmovapd	%ymm13, %ymm14
	vmovapd	%ymm12, %ymm13
	vmovapd	%ymm11, %ymm12
	vmovapd	%ymm10, %ymm11
	vmovapd	%ymm9, %ymm10
	vmovapd	%ymm8, %ymm9
	.p2align 4,,10
	.p2align 3
.L6:
	vmovupd	(%r10,%rax), %ymm2
	vmovapd	%ymm3, %ymm8
	vfmadd213pd	(%r8,%rax), %ymm2, %ymm8
	vfmadd213pd	(%rcx,%rax), %ymm15, %ymm2
	vfmadd231pd	(%rdi,%rax), %ymm14, %ymm8
	vfmadd231pd	(%rdi,%rax), %ymm13, %ymm2
	vfmadd231pd	(%r9,%rax), %ymm12, %ymm8
	vfmadd231pd	(%r9,%rax), %ymm11, %ymm2
	vfmadd231pd	(%rsi,%rax), %ymm10, %ymm8
	vfmadd231pd	(%rsi,%rax), %ymm9, %ymm2
	vmovupd	%ymm8, (%r8,%rax)
	vmovupd	%ymm2, (%rcx,%rax)
	addq	$32, %rax
	cmpq	%rax, %r11
	jne	.L6
	movq	256(%rsp), %r11
	vmovsd	%xmm4, %xmm4, %xmm3
	vmovsd	248(%rsp), %xmm4
	movq	%rcx, 272(%rsp)
	cmpq	%r11, 288(%rsp)
	je	.L7
	cmpq	$1, 160(%rsp)
	je	.L21
	leaq	(%rdx,%r11), %rdi
	leaq	0(%r13,%r11), %rsi
	movq	232(%rsp), %rcx
	vmovddup	%xmm1, %xmm15
	leaq	(%r12,%r11), %rax
	vmovddup	%xmm0, %xmm14
	vmovddup	%xmm7, %xmm10
	addq	%rbx, %r11
	vmovupd	(%r15,%r11,8), %xmm11
	movq	240(%rsp), %r11
	vmovddup	%xmm6, %xmm9
	vmovddup	%xmm5, %xmm8
	vmovddup	%xmm4, %xmm13
	vmovddup	%xmm3, %xmm2
	vmovddup	312(%rsp), %xmm12
	cmpq	$0, 224(%rsp)
	vfmadd213pd	(%r11), %xmm11, %xmm14
	vfmadd213pd	(%rcx), %xmm15, %xmm11
	vmovupd	(%r15,%rdi,8), %xmm15
	vfmadd132pd	%xmm15, %xmm11, %xmm9
	vfmadd132pd	%xmm15, %xmm14, %xmm10
	vmovupd	(%r15,%rsi,8), %xmm11
	vfmadd132pd	%xmm11, %xmm10, %xmm8
	vfmadd132pd	%xmm13, %xmm9, %xmm11
	vmovupd	(%r15,%rax,8), %xmm9
	vfmadd132pd	%xmm9, %xmm8, %xmm2
	vfmadd132pd	%xmm12, %xmm11, %xmm9
	vmovupd	%xmm2, (%r11)
	vmovupd	%xmm9, (%rcx)
	je	.L7
	movq	184(%rsp), %rax
.L8:
	movq	304(%rsp), %rsi
	leaq	(%rbx,%rax), %r11
	vmovsd	(%r15,%r11,8), %xmm2
	leaq	(%rdx,%rax), %r11
	addq	%rax, %rsi
	leaq	(%r14,%rsi,8), %rdi
	movq	296(%rsp), %rsi
	vfmadd213sd	(%rdi), %xmm2, %xmm0
	addq	%rax, %rsi
	leaq	(%r14,%rsi,8), %rsi
	vfmadd213sd	(%rsi), %xmm2, %xmm1
	vmovsd	(%r15,%r11,8), %xmm2
	leaq	0(%r13,%rax), %r11
	addq	%r12, %rax
	vfmadd132sd	%xmm2, %xmm0, %xmm7
	vmovsd	312(%rsp), %xmm0
	vfmadd132sd	%xmm2, %xmm1, %xmm6
	vmovsd	(%r15,%r11,8), %xmm1
	vfmadd132sd	%xmm1, %xmm7, %xmm5
	vfmadd132sd	%xmm1, %xmm6, %xmm4
	vmovsd	(%r15,%rax,8), %xmm1
	vfmadd132sd	%xmm1, %xmm5, %xmm3
	vfmadd132sd	%xmm1, %xmm4, %xmm0
	vmovsd	%xmm3, (%rdi)
	vmovsd	%xmm0, (%rsi)
.L7:
	movq	176(%rsp), %rax
	addq	$32, 280(%rsp)
	addq	$32, 264(%rsp)
	addq	%rax, %r10
	addq	%rax, %r9
	movq	168(%rsp), %rax
	movq	280(%rsp), %rcx
	addq	%rax, %rbx
	addq	%rax, %rdx
	addq	%rax, %r13
	addq	%rax, %r12
	movq	192(%rsp), %rax
	cmpq	%rax, %rcx
	jne	.L10
	movq	72(%rsp), %rbx
.L11:
	movq	104(%rsp), %rdx
	cmpq	%rdx, 288(%rsp)
	jle	.L5
	movq	288(%rsp), %rax
	movq	120(%rsp), %r13
	movq	%r8, 272(%rsp)
	movq	40(%rsp), %rdx
	movq	48(%rsp), %r9
	leaq	(%rax,%r13), %r11
	movq	112(%rsp), %rax
	movq	208(%rsp), %r10
	leaq	(%rbx,%rdx), %rsi
	leaq	(%rax,%rdx), %rcx
	movq	32(%rsp), %rdx
	addq	%rax, %rdx
	movq	%rdx, 280(%rsp)
	movq	%rax, %rdx
	.p2align 4,,10
	.p2align 3
.L12:
	cmpq	$2, 200(%rsp)
	jbe	.L22
	leaq	(%r15,%r9,8), %rdi
	xorl	%eax, %eax
	.p2align 4,,10
	.p2align 3
.L16:
	vmovupd	(%rbx,%rax), %ymm4
	vbroadcastsd	(%rdx), %ymm0
	vfmadd132pd	(%rdi,%rax), %ymm4, %ymm0
	vmovupd	%ymm0, (%rbx,%rax)
	vmovupd	(%rsi,%rax), %ymm5
	vbroadcastsd	(%rcx), %ymm0
	vfmadd132pd	(%rdi,%rax), %ymm5, %ymm0
	vmovupd	%ymm0, (%rsi,%rax)
	addq	$32, %rax
	cmpq	%r10, %rax
	jne	.L16
	movq	256(%rsp), %rax
	movq	%rax, %rdi
	cmpq	%rax, 288(%rsp)
	je	.L13
.L18:
	movq	288(%rsp), %r8
	subq	%rdi, %r8
	movq	%r8, 312(%rsp)
	cmpq	$1, %r8
	je	.L14
	leaq	0(%r13,%rdi), %r12
	vmovddup	(%rdx), %xmm1
	vmovddup	(%rcx), %xmm0
	leaq	(%r14,%r12,8), %r8
	leaq	(%r9,%rdi), %r12
	addq	%r11, %rdi
	vmovupd	(%r8), %xmm3
	leaq	(%r15,%r12,8), %r12
	leaq	(%r14,%rdi,8), %rdi
	vfmadd132pd	(%r12), %xmm3, %xmm1
	vmovupd	%xmm1, (%r8)
	movq	312(%rsp), %r8
	vmovupd	(%rdi), %xmm3
	vfmadd132pd	(%r12), %xmm3, %xmm0
	vmovupd	%xmm0, (%rdi)
	testb	$1, %r8b
	je	.L13
	andq	$-2, %r8
	addq	%r8, %rax
.L14:
	leaq	0(%r13,%rax), %rdi
	leaq	(%r14,%rdi,8), %r8
	leaq	(%r9,%rax), %rdi
	addq	%r11, %rax
	leaq	(%r15,%rdi,8), %rdi
	vmovsd	(%r8), %xmm6
	leaq	(%r14,%rax,8), %rax
	vmovsd	(%rdi), %xmm0
	vfmadd132sd	(%rdx), %xmm6, %xmm0
	vmovsd	%xmm0, (%r8)
	vmovsd	(%rdi), %xmm0
	vmovsd	(%rax), %xmm7
	vfmadd132sd	(%rcx), %xmm7, %xmm0
	vmovsd	%xmm0, (%rax)
.L13:
	movq	288(%rsp), %rax
	addq	$8, %rdx
	addq	$8, %rcx
	addq	%rax, %r9
	cmpq	%rdx, 280(%rsp)
	jne	.L12
	movq	272(%rsp), %r8
.L5:
	addq	$2, 152(%rsp)
	movq	144(%rsp), %rdx
	movq	80(%rsp), %rcx
	movq	88(%rsp), %rsi
	addq	%rdx, 136(%rsp)
	addq	%rdx, %r8
	movq	96(%rsp), %rdx
	addq	%rcx, 112(%rsp)
	addq	%rcx, %rbx
	movq	128(%rsp), %rcx
	movq	152(%rsp), %rax
	addq	%rsi, 120(%rsp)
	addq	%rcx, 304(%rsp)
	addq	%rcx, 296(%rsp)
	cmpq	%rdx, %rax
	je	.L42
	vzeroupper
	jmp	.L19
	.p2align 4,,10
	.p2align 3
.L22:
	xorl	%edi, %edi
	xorl	%eax, %eax
	jmp	.L18
.L21:
	movq	%r11, %rax
	jmp	.L8
.L42:
	vzeroupper
.L39:
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
.LFE15:
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
