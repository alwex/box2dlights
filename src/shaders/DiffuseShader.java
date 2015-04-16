package shaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class DiffuseShader {
	static final public ShaderProgram createShadowShader() {
		final String vertexShader = "attribute vec4 a_position;\n" //
				+ "attribute vec2 a_texCoord;\n" //
				+ "varying vec2 v_texCoords;\n" //
				+ "\n" //
				+ "void main()\n" //
				+ "{\n" //
				+ "   v_texCoords = a_texCoord;\n" //
				+ "   gl_Position = a_position;\n" //
				+ "}\n";

		// this is allways perfect precision
		final String fragmentShader = "#ifdef GL_ES\n" //
				+ "precision lowp float;\n" //
				+ "#define MED mediump\n"				
				+ "#else\n"				
				+ "#define MED \n"
				+ "#endif\n" //
				+ "varying MED vec2 v_texCoords;\n" //
				+ "uniform sampler2D u_texture;\n" //
				+ "uniform sampler2D u_texture1;\n" //
				+ "uniform  vec4 ambient;\n"
					+ "void main()\n"//
				+ "{\n" //
				+ "  vec4 backColor = texture2D(u_texture1, vec2(v_texCoords.x, 1.0 - v_texCoords.y));\n"
				+ "  if (backColor.a > 0.0) {\n"
				+ "    gl_FragColor.rgb = (ambient.rgb + texture2D(u_texture, v_texCoords).rgb);\n"
				+ "    gl_FragColor.a = backColor.a;\n"
				+ "  } else {\n"
				+ "    gl_FragColor = vec4(1.0, 1.0, 1.0, 0.0);"
				+ "  }"
				+ "}\n";
		ShaderProgram.pedantic = false;
		ShaderProgram shadowShader = new ShaderProgram(vertexShader,
					fragmentShader);
		if (shadowShader.isCompiled() == false) {
			Gdx.app.log("ERROR", shadowShader.getLog());

		}

		return shadowShader;
	}

}
