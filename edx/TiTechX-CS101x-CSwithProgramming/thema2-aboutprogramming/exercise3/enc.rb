# enc.rb
# string encrypt by caesar encode
# input: string m
# output: encrypted string (shift: k)

code_a = 97
k = 3

m = gets.chomp
leng = m.length
b = Array.new(leng, 0)
a = m.unpack("C*")

for i in 0..(leng-1)
  sa = a[i] - code_a
  if (0<=sa && sa<=26-1) then
    sa = sa + k
    if sa >= 26 then
      sa = sa % 26
    end
  end
  # print(m[i], ", ", a[i], ", ", sa, "\n")
  b[i] = sa + code_a
end

c = b.pack("C*")
puts(c)
