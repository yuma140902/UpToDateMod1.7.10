package yuma140902.yumalib.api.model

import net.minecraft.util.IIcon
import net.minecraftforge.common.util.ForgeDirection

case class YLBlockModel(
                         textures: Map[TextureId, IIcon],
                         ambientOcclusion: Boolean = true,
                         elements: Seq[Element])

case class Element(
                    cuboid: Cuboid,
                    //TODO: rotation,
                    //TODO: shade: Boolean,
                    down: Face,
                    up: Face,
                    north: Face,
                    south: Face,
                    west: Face,
                    east: Face
                  )

case class Cuboid(from: Point, to: Point)

case class Point(x: Float, y: Float, z: Float)

case class Face(
                 //TODO: auto-calculate uv range: Option[UvRange]
                 uv: UvRange,
                 texture: TextureId,
                 cullface: ForgeDirection = ForgeDirection.UNKNOWN
                 //TODO: rotation
               )

case class UvRange(uMin: Float, vMin: Float, uMax: Float, vMax: Float)

case class TextureId(name: String)