package com.cs407.lab09

/**
 * Represents a ball that can move. (No Android UI imports!)
 *
 * Constructor parameters:
 * - backgroundWidth: the width of the background, of type Float
 * - backgroundHeight: the height of the background, of type Float
 * - ballSize: the width/height of the ball, of type Float
 */
class Ball(
    private val backgroundWidth: Float,
    private val backgroundHeight: Float,
    private val ballSize: Float
) {
    var posX = 0f
    var posY = 0f
    var velocityX = 0f
    var velocityY = 0f
    private var accX = 0f
    private var accY = 0f

    private var isFirstUpdate = true

    init {
        reset()
    }

    /**
     * Updates the ball's position and velocity based on the given acceleration and time step.
     * (See lab handout for physics equations)
     */
    fun updatePositionAndVelocity(xAcc: Float, yAcc: Float, dT: Float) {
        if(isFirstUpdate) {
            isFirstUpdate = false
            accX = xAcc
            accY = yAcc
            return
        }

        posX += velocityX * dT + (1f/6f)*(dT*dT)*(2*accX + xAcc)
        posY += velocityY * dT + (1f/6f)*(dT*dT)*(2*accY + yAcc)

        velocityX += accX*dT + ((xAcc - accX)/2f) * dT
        velocityY += accY*dT + ((yAcc - accY)/2f) * dT

        accX = xAcc
        accY = yAcc
    }

    /**
     * Ensures the ball does not move outside the boundaries.
     * When it collides, velocity and acceleration perpendicular to the
     * boundary should be set to 0.
     */
    // Ball.kt -> checkBoundaries

    fun checkBoundaries() {
        // Right Wall
        if (posX + ballSize / 2f >= backgroundWidth) {
            posX = backgroundWidth - ballSize / 2f // Clamp position
            velocityX = 0f
            accX = 0f
        }
        // Left Wall
        else if (posX - ballSize / 2f <= 0f) {
            posX = ballSize / 2f // Clamp position
            velocityX = 0f
            accX = 0f
        }

        // Bottom Wall
        if (posY + ballSize / 2f >= backgroundHeight) {
            posY = backgroundHeight - ballSize / 2f // Clamp position
            velocityY = 0f
            accY = 0f
        }
        // Top Wall
        else if (posY - ballSize / 2f <= 0f) {
            posY = ballSize / 2f // Clamp position
            velocityY = 0f
            accY = 0f
        }
    }

    /**
     * Resets the ball to the center of the screen with zero
     * velocity and acceleration.
     */
    fun reset() {
        // (Reset posX, posY, velocityX, velocityY, accX, accY, isFirstUpdate)
        isFirstUpdate = true
        posX = backgroundWidth / 2f
        posY = backgroundHeight / 2f
        velocityX = 0f
        velocityY = 0f
        accX = 0f
        accY = 0f
    }
}