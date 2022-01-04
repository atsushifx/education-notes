puts('Number?')
a = gets().to_i
b = gets().to_i
shou = 0
while (a >= b)
	a = a - b
	shou = shou + 1
end
puts("shou=", shou)
