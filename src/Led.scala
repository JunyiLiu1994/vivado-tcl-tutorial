package vivado.tutorial

import spinal.core._
import spinal.lib._
import spinal.lib.bus.amba4.axi._

case class SimpleLed() extends Component {
  val io = slave(PynqZ2Io())
  VivadoAxiInference(
    io.axi
  ) // add inference hints for vivado to identify the axi port in the block design
  io.axi.setBlocked() // disable axi for this module

  io.rgbLedOff()

  val led = Reg(Bool()) init (False)
  io.led := (led #* 4 & io.sw.resize(4))

  val counter = Reg(UInt(28 bits)) init (0)
  counter := counter + 1

  val counterMsb = counter(27 downto 26)
  val switchNumber = UInt(2 bits)
  switchNumber := io.sw.asUInt

  val doLedToggle = counterMsb === switchNumber
  when(switchNumber === 0) {
    led := False
  } elsewhen (doLedToggle) {
    led := !led
    counter := 0
  }
}

object SimpleLed extends App {
  SpinalConfig(
    mode = Verilog,
    targetDirectory = "./fpga/src/rtl/"
  ).generate {
    SimpleLed()
  }
}

case class AxiLed() extends Component {
  val io = slave(PynqZ2Io())
  VivadoAxiInference(
    io.axi
  ) // add inference hints for vivado to identify the axi port in the block design

  val led4 = Bits(3 bits)
  io.led4_r := led4(0)
  io.led4_g := led4(1)
  io.led4_b := led4(2)
  val led5 = Bits(3 bits)
  io.led5_r := led5(0)
  io.led5_g := led5(1)
  io.led5_b := led5(2)

  val driveAddress = Reg(UInt(4 bits))
  when(io.axi.aw.valid) {
    driveAddress := io.axi.aw.addr(2, 4 bits)
  }
  when(io.axi.ar.valid) {
    driveAddress := io.axi.ar.addr(2, 4 bits)
  }

  io.led := driveAddress.asBits

  val offset = 0x40000000
  val factory = Axi4SlaveFactory(io.axi)
  factory.read(io.sw, offset + 4)
  factory.read(io.btn, offset + 8)
  factory.drive(led4, offset + 12)
  factory.drive(led5, offset + 16)
}

object AxiLed extends App {
  SpinalConfig(
    mode = Verilog,
    targetDirectory = "./fpga/src/rtl/"
  ).generate {
    AxiLed()
  }
}

case class AxiCrossbarLed() extends Component {
  val io = slave(PynqZ2Io())
  VivadoAxiInference(
    io.axi
  ) // add inference hints for vivado to identify the axi port in the block design

  val led4 = Bits(3 bits)
  io.led4_r := led4(0)
  io.led4_g := led4(1)
  io.led4_b := led4(2)
  val led5 = Bits(3 bits)
  io.led5_r := led5(0)
  io.led5_g := led5(1)
  io.led5_b := led5(2)

  val driveAddress = Reg(UInt(4 bits))
  when(io.axi.aw.valid) {
    driveAddress := io.axi.aw.addr(2, 4 bits)
  }
  when(io.axi.ar.valid) {
    driveAddress := io.axi.ar.addr(2, 4 bits)
  }

  io.led := driveAddress.asBits

  val crossbar = Axi4CrossbarFactory()
  val axi0 = cloneOf(io.axi)
  val axi1 = cloneOf(io.axi)
  crossbar.addSlaves(
    axi0 -> (BigInt(0x40000000L), BigInt(0x01000000L)),
    axi1 -> (BigInt(0x41000000L), BigInt(0x01000000L))
  )
  crossbar.addConnections(
    io.axi -> List(axi0, axi1)
  )
  crossbar.addPipelining(io.axi)((crossbar, bridge) => {
    crossbar.readCmd.s2mPipe().m2sPipe() >> bridge.readCmd
    crossbar.readRsp << bridge.readRsp.s2mPipe().m2sPipe()
  })((crossbar, bridge) => {
    crossbar.writeCmd.s2mPipe().m2sPipe() >> bridge.writeCmd
    crossbar.writeData.s2mPipe().m2sPipe() >> bridge.writeData
    crossbar.writeRsp << bridge.writeRsp.s2mPipe().m2sPipe()
  })
  crossbar.build()

  val offset0 = 0x40000000
  val offset1 = 0x41000000
  val factory0 = Axi4SlaveFactory(axi0)
  val factory1 = Axi4SlaveFactory(axi1)
  factory0.read(io.sw, offset0 + 4)
  factory1.read(io.btn, offset1 + 4)
  factory0.drive(led4, offset0 + 8)
  factory1.drive(led5, offset1 + 8)
}

object AxiCrossbarLed extends App {
  SpinalConfig(
    mode = Verilog,
    targetDirectory = "./fpga/src/rtl/"
  ).generate {
    AxiCrossbarLed()
  }
}
