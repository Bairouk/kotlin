// !DIAGNOSTICS: -UNUSED_VARIABLE
// !WITH_NEW_INFERENCE

fun <T> foo(array: Array<Array<T>>): Array<Array<T>> = array

fun test(array: Array<Array<out Int>>) {
    <!TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>foo<!>(array)

    val f: Array<out Array<out Int>> = <!TYPE_INFERENCE_PARAMETER_CONSTRAINT_ERROR!>foo<!>(<!TYPE_MISMATCH!>array<!>)
}
