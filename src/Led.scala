package vivado.tutorial

import spinal.core._
import spinal.lib._

case class SimpleLed() extends Component {
  val io = slave(PynqZ2Io())
  VivadoAxiInference(io.axi) // add inference hints for vivado to identify the axi port in the block design
  io.axi.setBlocked() // disable axi for this module

  io.rgbLedOff()

  val led = Reg(Bool()) init(False)
  io.led := (led #* 4 & io.sw.resize(4))

  val counter = Reg(UInt(28 bits)) init(0)
  counter := counter + 1

  val counterMsb = counter(27 downto 26)
  val switchNumber = UInt(2 bits)
  switchNumber := io.sw.asUInt

  val doLedToggle = counterMsb === switchNumber
  when(switchNumber === 0) {
    led := False
  } elsewhen(doLedToggle) {
    led := !led
    counter := 0
  }
}

object SimpleLed extends App {
  SpinalConfig(
    mode = Verilog,
    targetDirectory = "./fpga/src/rtl/",
  ).generate{
    SimpleLed()
  }
}