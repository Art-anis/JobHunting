package com.nerazim.emtest

//общий JSON-объект
data class Data(
    val offers: List<Offer> = listOf(), //список рекомендаций
    val vacancies: List<Vacancy> = listOf() //список вакансий
)

//рекомендации
data class Offer(
    val id: String, //идентификатор
    val title: String, //название
    val link: String //ссылка
)

//вакансия
data class Vacancy(
    val id: String, //идентификатор
    val lookingNumber: Int, //сколько людей смотрит вакансию
    val title: String, //название
    val address: Address, //адрес компании
    val company: String, //название компании
    val experience: Experience, //опыт работы
    val publishedDate: String, //дата публикации
    val isFavorite: Boolean, //добавлена ли вакансия с избранное
    val salary: Salary, //зарплата
    val schedules: List<String>, //варианты занятости
    val appliedNumber: Int? = null, //число откликнувшихся
    val description: String? = null, //описание вакансии
    val responsibilities: String, //ответственности
    val questions: List<String> //список вопросов
)

//адрес компании
data class Address(
    val town: String, //город
    val street: String, //улица
    val house: String //дом
)

//опыт работы
data class Experience(
    val previewText: String, //кратко об опыте
    val text: String //полная строка
)

//зарплата
data class Salary(
    val short: String? = null, //кратко о зарплате
    val full: String //полная строка
)
