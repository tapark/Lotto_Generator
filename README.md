# 로또번호 생성기

### by lazy를 통한 늦은 초기화 & NumberPicker
~~~kotlin
private val numberPicker: NumberPicker by lazy {
	findViewById<NumberPicker>(R.id.numberPicker)
}
// NumberPicker의 min / max 설정
numberPicker.minValue = 1
numberPicker.maxValue = 45
// NumberPicker의 숫자(값)에 접근
numberPicker.value
~~~

### List, HashSet의 특정 객체(value) 존재 확인
~~~kotlin
numberHashSet.contains(value)
numberList.contains(value)
// List : 순서를 가지며 중복값을 허용
// HashSet : 순서없이 중복값을 허용X
// List에는 1 ~ 45의 숫자를 넣고 HashSet에는 결정된(뽑은) 숫자를 저장
~~~

### Random Int 생성
~~~kotlin
// List에 1 ~ 45의 숫자를 저장
val numberList = mutableListOf<Int>().apply {
	for (i in 1..45) {
		if (pickNumberSet.contains(i))
			continue // / 사용자가 사전에 선택한 숫자 제외
		this.add(i)
	}
}
// List의 순서를 랜덤하게 변경
numberList.shuffle()
~~~

### 6개의 숫자를 List 형태로 저장 후 정렬
~~~kotlin
val resultList = pickNumberSet.toList() + numberList.subList(0, 6 - pickNumberSet.size)
return resultList.sorted()
~~~

### TextView의 사용자정의 background 적용
~~~kotlin
private fun setNumberBackground(number:Int, textView: TextView) {
    when (number) {
        in 1..10 -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_yellow)
        in 11..20 -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_blue)
        in 21..30 -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_red)
        in 31..40 -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_gray)
        else -> textView.background = ContextCompat.getDrawable(this, R.drawable.ball_green)
        }
~~~

### forEach 와 forEachIndex
~~~kotlin
// forEach : 명시적으로 number 변수에 값을 지정
numberList.forEach { number ->
    println("$number")
}
// forEach : 암묵적으로 it 키워드 변수 사용
numbrtList.forEach {
    println(it)
}
// forEachIndex : n번째 index와 해당하는 number 변수
numbrtList.forEach { index, number ->
	println("${index}번째의 값은 ${number}입니다.")
}
~~~
