puts('Number?')
a = gets().to_i
b = gets().to_i
shou = 0
while (a > 0)
	a = a - b
	shou = shou + 1
	if a < 0
		shou = shou - 1
		a = 0
	end
end
puts("shou=", shou)
