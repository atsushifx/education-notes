puts('Number?')
x = gets().to_i
y = gets().to_i
r1 = x % y
r2 = -x % y
r3 = x % -y
r4 = -x % -y

puts("x % y = "  + r1.to_s)
puts("-x % y = " + r2.to_s)
puts("x % -y = " + r3.to_s)
puts("-x % -y = " + r4.to_s)
