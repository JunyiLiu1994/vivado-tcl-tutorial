create_bd_cell -type ip -vlnv xilinx.com:ip:processing_system7:5.5 ZynqPs
apply_bd_automation -rule xilinx.com:bd_rule:processing_system7 -config {apply_board_preset "1" }  [get_bd_cells ZynqPs]
connect_bd_net [get_bd_pins ZynqPs/FCLK_CLK0] [get_bd_pins ZynqPs/M_AXI_GP0_ACLK]