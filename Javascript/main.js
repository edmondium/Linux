const age = 10;
const name = 'Dicoding';
console.log(`Aku ${name}, umurku ${age} tahun.`);

// Teks ini akan diabaikan oleh interpreter
console.log('Hai, Readers!');
console.log('Hai, JavaScript!');
// console.log('Hai, Dicoding!');

/*
 * TODO
 * 1. Buatlah variabel bernama `PI` dan isikan dengan nilai 3.14
 * 2. Cetak nilai variabel PI di terminal menggunakan console.log
 */

const PI = '3.14';
console.log(PI);

const id = 123;
let username = 'Dicoding';
console.log(id); // output: 123
console.log(username); //output: Dicoding

//let username = 'Dicoding';
console.log('Sebelum diubah:', username); // output: Sebelum diubah: Dicoding
username = 'dicodingacademy';
console.log('Setelah diubah:', username); // output: Setelah diubah: dicodingacademy

const currentYear = new Date().getFullYear();
const text = `Sekarang adalah tahun ${currentYear}.`;
console.log(text);

const result = 50 / 0;
console.log(result); // output: Infinity

const hasil = Number('Dicoding');
console.log(hasil); // output: NaN

const completed = true;
const passed = false;
console.log(completed, passed); // output: true false

const isGreater = 5 > 2;
console.log(isGreater); // output: true (5 lebih besar dari 2)

let message = null;
console.log(message); // output: null

let pesan;
console.log(pesan); // output: undefined

message = undefined;
console.log(message); // output: undefined

const name1 = { first: 'Dicoding', last: null };
const name2 = { first: 'Dicoding', last: undefined };
console.log(JSON.stringify(name1)); // output: {"first":"Dicoding","last":null}
console.log(JSON.stringify(name2)); // output: {"first":"Dicoding"}

const number = 123;
const boolean = true;
const strNumber = String(number);
const strBoolean = boolean.toString();
console.log(strNumber); // output: "123"
console.log(strBoolean); // output: "true"

const stringNumber = '123';
const strFloat = '3.14';
const bool = true;
const numFromString = Number(stringNumber);
const floatFromString = Number(strFloat);
const numFromBoolean = Number(bool);
console.log(numFromString); // output: 123
console.log(floatFromString); // output: 3.14
console.log(numFromBoolean); // output: 1

const cm = '20cm';
const px = '64px';
const intFromCM = parseInt(cm);
const intFromPX = parseInt(px);
console.log(intFromCM); // output: 20
console.log(intFromPX); // output: 64

const centimeter = '20.55cm';
const pixel = '64.23px';
const floatFromCM = parseFloat(centimeter);
const floatFromPX = parseFloat(pixel);
console.log(floatFromCM); // output: 20.55
console.log(floatFromPX); // output: 64.23

const NUMBER = 123;
const string = 'Dicoding';
const empty = null;
const boolFromNumber = Boolean(NUMBER);
const boolFromString = Boolean(string);
const boolFromNull = Boolean(empty);
console.log(boolFromNumber); // output: true
console.log(boolFromString); // output: true
console.log(boolFromNull); // output: false

const umur = 20;
const MESSAGE = 'Umurku: ' + umur;
console.log(MESSAGE); // output: Umurku: 20

const strNUMBER = '123';
const RESULT = strNUMBER * 2;
console.log(RESULT); // output: 246

const BOOL = true;
const Result = 1 + BOOL;
console.log(Result); // output: 2

const a = 10;
const b = 12;
console.log(a < b); // output: true
console.log(a > b); // output: false

const first = 'bekerja';
const second = 'sama';
const merged = first + second;
console.log(merged); // Output: bekerjasama