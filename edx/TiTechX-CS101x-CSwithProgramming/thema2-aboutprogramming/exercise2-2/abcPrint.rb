##
# abcPrint.rb
# only lower alphabet output
#
# @author	atsushifx
# @date		2022-01-10
# @version	1.0.0

code_a = 97
puts("please input strings:")
ss = gets().chomp
leng = ss.length
aa = ss.unpack("C*")

for  i in 0..(leng-1)
	sa = aa[i] - code_a
  if (0<=sa && sa<=26-1) then
    puts(ss[i])
    # print(ss[i], ", ", aa[i], ", ", sa, "\n")
  end
end
