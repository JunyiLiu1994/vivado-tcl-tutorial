create_bd_cell -type ip -vlnv xilinx.com:ip:processing_system7:5.5 ZynqPS
apply_bd_automation -rule xilinx.com:bd_rule:processing_system7 -config {apply_board_preset "1" }  [get_bd_cells ZynqPS]
connect_bd_net [get_bd_pins ZynqPS/FCLK_CLK0] [get_bd_pins ZynqPS/M_AXI_GP0_ACLK]