def collatz (number):
  numbers = [number]
  if number <= 0:
      return 'invalid choice'
  while number != 1:
      if number%2 == 0 :
          number = number // 2
          numbers.append(number)
      else:
          number = (number*3)+1
          numbers.append(number)
   
  return numbers

a = int(input('give a number: '))
result = collatz(a)
print(result)
