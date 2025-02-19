# Опис `SoftwareSelection.java`



## Ідея скрипту:

Скрипт ілюструє використання асинхронних операцій у Java для моделювання процесу вибору між кількома варіантами програмного забезпечення. Кожен варіант оцінюється за кількома критеріями, і на основі порівняння результатів визначається, який з варіантів є найкращим. Використання `CompletableFuture` дозволяє виконати цю задачу асинхронно, не блокуючи основний потік виконання програми.

## Як працює програма:

1. Програма генерує випадкові оцінки для трьох критеріїв кожного з програм (Software A, Software B, Software C).
2. Оцінки для кожного програмного забезпечення додаються, щоб отримати загальний бал.
3. Програма порівнює ці бали і визначає, яке програмне забезпечення має найбільшу суму.
4. Результат виводиться в консоль.

## Приклад виводу:

```
GENERATED Software A : [45, 78, 23]
GENERATED Software B : [67, 52, 44]
GENERATED Software C : [82, 65, 21]
Chosen BEST_SOFT: Software A - Всього балів: 146
```

## Моменти:

- Кожне програмне забезпечення генерує випадкові дані, тому результат може змінюватися при кожному запуску програми.
- Асинхронна обробка допомагає забезпечити ефективне виконання навіть за умов великої кількості обчислень чи затримок.