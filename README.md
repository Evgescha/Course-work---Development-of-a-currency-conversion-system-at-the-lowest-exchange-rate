# Система конвертации валют по низкому курсу (2021)
Разработка системы конвертации валют, по наиболее никому курсу. Java Swing Шаблоны проектирования

## Постановка задачи

Создайте демонстрационное java приложение, в котором используются шаблоны проектирования. Подробно опишите задачу, которую вы собираетесь решить при разработке приложения. Обратите особое внимание на форматы входных и выходных данных. 

### Описание сущностей

1. Опишите все ваши классы и интерфейсы разрабатываемого приложения и отношения между ними. Диаграммы классов настоятельно рекомендуются. 
1. Полное описание любого класса включает его назначение и описание полей, конструкторов, методов, вложенных и внутренних классов. 
1. Описание любого поля включает его назначение, все модификаторы, тип. 
1. Описание любого метода включает его назначение, все модификаторы, тип возвращаемого значения, выбрасываемые исключения. 

### Реализация шаблонов проектирования

Опишите все шаблоны проектирования GOF, которые вы использовали в разработанном приложении. 
Описание любого шаблона проектирования включает его назначение, диаграмму классов, иллюстративный Java-код, достигнутые результаты. 

Вы должны рассмотреть 2+ шаблона проектирования GOF, которые вы не использовали в разработанном приложении. 
Полное описание любого шаблона проектирования включает в себя краткую формулировку проблемы и ее очевидную реализацию, обнаруженные недостатки, идею их устранения, диаграмму классов, иллюстративный java-код, достигнутые результаты. 


### Тестирование приложения

Запустив приложение, пользователю будет показано главное окно (форма) программы.

![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/69cb9ca6-169f-403e-a052-da6011cc4415)

Рисунок 2.4 – Главная форма 

На этой странице пользователь может выбрать одно из трех меню: перейти на страницу валют, перейти на страницу курсов различных банков или же открыть форму обмена валют. Пройдем каждый вариант по очереди.
Перейдем на страницу валют, пользователю будет отображена форма как на рисунке 2.5.
 
 ![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/d562ad3f-b95b-4ec2-b000-ebcbd59c5bac)

Рисунок 2.5 – Форма валют

На этой форме пользователь может посмотреть список всех валют, которые участвуют в жизни приложения. При необходимости можно добавить или изменить существующую валюту, или же удалить ее вовсе. На всякий случай сделана проверка, чтобы нельзя было добавить в таблицу пустые значения, а также проверка на ввод запрещенных символов (цифр) в поле.
 
 ![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/2b60018e-5caa-45b4-858c-cba3a1d80a68)

Рисунок 2.6 – Ошибка при неправильном заполнении поля

Для дальнейшей работы добавим собственную валюту
 

![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/d79f14ba-0f7c-4b8e-a639-5601b6588cdf)

Рисунок 2.7 – Добавление собственной валюты

Закроем форму и перейдем из главной страницы в курс валют.  Пользователю будет показана форма, отображенная на рисунке 2.8.

 ![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/aa939ce1-4828-454f-be16-6aa5884f1eeb)

Рисунок 2.8 – Форма курс валют

На данной форме отображено, какая валюта как относится к доллару. Иначе говоря, если за 1 белорусский рубль можно купить 0,393 доллара, то так и должно быть занесено в эту таблицу. Занесем в нее свои значения. К примеру, пусть наша валюта будет с долларом в отношении 2 к 1, т.е. за 1 единицу нашей валюты можно купить 2 доллара.
 
 ![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/b5b5b43e-5450-499e-8bf2-f78fc73f9575)

Рисунок 2.9 – Добавление курса своей валюте.

Теперь остается перейти на последнюю форму приложения и проверить работоспособность. Форма обмена показана на рисунке 2.10.

 ![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/a80f1910-54a1-4b51-b0c7-ea3577441df8)

Рисунок 2.10 – Форма обмена

Попробуем перевести одну единицу нашей валюты в доллары и посмотрим что выйдет.
 
 ![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/da701106-6511-4f21-bdc1-32275d27de65)

Рисунок 2.11 – Результат перевода собственной валюты в доллары

Результат ожидаемый. Нам отображен ход действий, с помощью которого можно перевести валюты с наилучшей выгодой и по меньшему курсу. 
Сперва в сообщении показывает, из какой валюты и в какую мы переводим. Затем отображается лучший курс для перевода текущей валюты в доллары, поскольку весь курс высчитывается по отношению к нему. Название банков для перевода в доллары и в желаемую валюты показаны после этого. Последней строкой показан итоговый результат: сколько новой валюты будет получено.
Для подтверждения эксперимента, в курсах валют поставим нашу валюту по курсу 3 и 0.5 к доллару и посмотрим, выберет ли приложение минимальный курс.

 ![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/f184dd9a-cc15-4e18-80ff-fecf1eb812ac)

Рисунок 2.12 – Курс собственной валюты

Подтверждение тому, что по лучшему курсу за 1 доллар мы можем купить 2 единицы нашей валюты отображен на рисунке 2.13. Если бы приложение выбрало не наименьший курс, 3 единицы новой валюты стоили бы только 1 доллар, а так 1 единица новой валюты стоят целых 0.5 доллара по наименьшему курсу.
 
 ![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/fd1df093-7e8d-4b85-854a-884e80d0c38b)

Рисунок 2.13 – Перевод из долларов в нашу валюты

 ![image](https://github.com/Evgescha/2101-2091.-Development-of-a-currency-conversion-system-at-the-lowest-exchange-rate/assets/38140129/abfb71ce-0b55-47fb-bf95-8a6a7c818212)

Рисунок 2.14 – Перевод из нашей валюты в доллары
