# Restaurant_menu
Программа - электронное меню.

Программа нужна для автоматизации работы в ресторане.

Суть программы:
директор ресторана хочет, чтобы:
1. на каждом столике лежал планшет, через который можно было бы сделать заказ;
2. пока заказ готовится, на планшете показывалась реклама;
3. в конце рабочего дня была возможность посмотреть различную статистику:
а) загрузки повара;
б) сумму выручки за заказы;
в) сумму выручки за показы рекламы.

Мы будем разрабатывать ядро, без UI. UI ты потом сможешь дописать самостоятельно, сейчас ограничимся консольным интерфейсом.
Также мы часть данных захардкодим, ты потом сможешь прикрутить БД, и работать с ней.

Выделим несколько фич, которые будем реализовывать:

Первая:
- созданный посетителем заказ будет автоматически поступать к повару;
- повар будет готовить его какое-то время и отмечать приготовленным;
- после этого официант будет относить его.

Вторая:
- подобрать нужные рекламные ролики из списка оплаченных;
- отображать рекламные ролики во время приготовления заказа;
- максимизировать прибыль от показа рекламы.

Третья:
- подсчитывать статистику;
- отображать статистику директору.
