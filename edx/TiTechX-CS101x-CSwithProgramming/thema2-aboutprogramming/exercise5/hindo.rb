# hindo.rb
# input: message
# output: alphabet histgram

code_a = 97
angobun = gets.chomp
leng = angobun.length
a = angobun.unpack("C*")
h = Array.new(26, 0)

for i in 0..(leng-1)
  sa = a[ i ] - code_a
  if (0<=sa && sa<=26-1) then
    h[sa] = h[sa] + 1
  end
end
puts(h)
