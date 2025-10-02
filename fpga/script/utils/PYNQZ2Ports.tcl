create_bd_port -dir I -type clk sysclk
connect_bd_net [get_bd_ports sysclk] [get_bd_pins Top/clk] 

create_bd_port -dir I -from 1 -to 0 -type data sw
connect_bd_net [get_bd_ports sw] [get_bd_pins Top/io_sw] 

create_bd_port -dir I -from 3 -to 0 -type data btn
connect_bd_net [get_bd_ports btn] [get_bd_pins Top/io_btn] 

create_bd_port -dir O -from 3 -to 0 -type data led
connect_bd_net [get_bd_ports led] [get_bd_pins Top/io_led] 

create_bd_port -dir O led4_b
connect_bd_net [get_bd_ports led4_b] [get_bd_pins Top/io_led4_b] 
create_bd_port -dir O led4_g
connect_bd_net [get_bd_ports led4_g] [get_bd_pins Top/io_led4_g] 
create_bd_port -dir O led4_r
connect_bd_net [get_bd_ports led4_r] [get_bd_pins Top/io_led4_r] 
create_bd_port -dir O led5_b
connect_bd_net [get_bd_ports led5_b] [get_bd_pins Top/io_led5_b] 
create_bd_port -dir O led5_g
connect_bd_net [get_bd_ports led5_g] [get_bd_pins Top/io_led5_g] 
create_bd_port -dir O led5_r
connect_bd_net [get_bd_ports led5_r] [get_bd_pins Top/io_led5_r] 